package com.github.djbamba.db0921.dao;

import com.github.djbamba.db0921.model.Brand;
import com.github.djbamba.db0921.model.Tool;
import com.github.djbamba.db0921.model.ToolType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * DAO for Tools
 */
public class ToolRentalDao implements RentalDao<Tool> {
  private List<Tool> tools;

  public ToolRentalDao() {
    initTools();
  }

  @Override
  public Optional<Tool> findByID(String id) {
    return this.tools.stream().filter(tool -> tool.getCode().equalsIgnoreCase(id)).findFirst();
  }

  @Override
  public List<Tool> getAll() {
    return Collections.unmodifiableList(this.tools);
  }

  private void initTools() {
    tools = new ArrayList<>();

    tools.add(new Tool(ToolType.LADDER, Brand.WERNER, "LADW"));
    tools.add(new Tool(ToolType.CHAINSAW, Brand.STIHL, "CHNS"));
    tools.add(new Tool(ToolType.JACKHAMMER, Brand.RIDGID, "JAKR"));
    tools.add(new Tool(ToolType.JACKHAMMER, Brand.DEWALT, "JAKD"));
  }
}
