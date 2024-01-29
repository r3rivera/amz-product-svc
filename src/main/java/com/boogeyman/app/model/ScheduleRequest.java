package com.boogeyman.app.model;

import com.boogeyman.app.constraints.RangeCheck;
import lombok.Data;

@Data
@RangeCheck.List({
        @RangeCheck(
                startRange = "startDate",
                endRange = "endDate",
                message = "Passwords do not match!"
        )
})
public class ScheduleRequest {
    private String title;
    private String description;
    private String location;
    private boolean blocked;
    private String startDate;
    private String endDate;
}
