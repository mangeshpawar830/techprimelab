package com.techprime.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardChartData {
    private String department;
    private Long registeredCount;
    private Long closedCount;
    private Double successPercentage;
}
