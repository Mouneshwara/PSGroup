package in.psgroup.psgroup.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class GeneralEnquiryBean implements Parcelable {
    String question,answer;

    public GeneralEnquiryBean(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    protected GeneralEnquiryBean(Parcel in) {
        question = in.readString();
        answer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GeneralEnquiryBean> CREATOR = new Creator<GeneralEnquiryBean>() {
        @Override
        public GeneralEnquiryBean createFromParcel(Parcel in) {
            return new GeneralEnquiryBean(in);
        }

        @Override
        public GeneralEnquiryBean[] newArray(int size) {
            return new GeneralEnquiryBean[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
