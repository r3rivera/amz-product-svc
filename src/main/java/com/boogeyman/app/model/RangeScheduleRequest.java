package com.boogeyman.app.model;

import com.boogeyman.app.constraints.DateRangeCheck;
import lombok.Data;

@Data
@DateRangeCheck.List({
        @DateRangeCheck(
                startRange = "startDate",
                endRange = "endDate",
                message = "startDate must be before the EndDate"
        )
})
public class RangeScheduleRequest {
    private String startDate;
    private String endDate;
}
