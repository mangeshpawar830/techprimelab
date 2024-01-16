package com.techprime.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter@Setter
public class ProjectInfoResponse {
	private Long total;
    private Long cancel;
    private Long running;
    private Long registered;
    private Long closed;
    private Long delayedRunning;


}
