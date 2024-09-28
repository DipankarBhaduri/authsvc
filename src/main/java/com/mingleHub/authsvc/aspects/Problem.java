package com.mingleHub.authsvc.aspects;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Problem {
	private String title;
	private String message;
}
