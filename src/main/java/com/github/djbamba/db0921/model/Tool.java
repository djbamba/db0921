package com.github.djbamba.db0921.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Tool {
private final ToolType toolType;
private final Brand brand;
private final String toolCode;
}
