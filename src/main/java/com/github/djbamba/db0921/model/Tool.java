package com.github.djbamba.db0921.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tool {
private final ToolType toolType;
private final Brand brand;
private final String toolCode;
}
