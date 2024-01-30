package com.boogeyman.app.model;

import com.boogeyman.app.constraints.DateRangeCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@DateRangeCheck.List({
        @DateRangeCheck(
                startRange = "startDate",
                endRange = "endDate",
                message = "startDate must be before the EndDate"
        )
})
public class ScheduleRequest {
    @NotNull
    private String title;
    private String description;
    private String location;
    private boolean blocked;

    @NotNull
    private String startDate;
    @NotNull
    private String endDate;
}
