package in.psgroup.psgroup;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubePlayerExampleActivity extends YouTubeBaseActivity {
    private final String API_KEY = "AIzaSyD-OAHYcSfmFc49z61bz5PFJ4IdvFCfQYc";
    private final String VIDEO_CODE = "AGBjI0x9VbM";
    YouTubePlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_player_example);

        player = (YouTubePlayerView)findViewById(R.id.player);
        player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.loadVideo(VIDEO_CODE);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(YouTubePlayerExampleActivity.this,youTubeInitializationResult.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
