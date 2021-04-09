package in.psgroup.psgroup.FilterModule.Interfaces;

import in.psgroup.psgroup.FilterModule.Models.Location;
import in.psgroup.psgroup.FilterModule.Models.PriceInterval;
import in.psgroup.psgroup.FilterModule.Models.PropertyStatus;
import in.psgroup.psgroup.FilterModule.Models.PropertyType;

/**
 * Created by Codebele on 4/17/2019.
 */
public interface FilterInterface {
    void setPropertyLocation(Location location);

    void setPriceInterval(PriceInterval priceInterval);

    void setPropertyStatus(PropertyStatus propertyStatus);

    void setPropertyType(PropertyType propertyType);
}
