package com.zss.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record implements Serializable {

  private long id;
  private long userId;
  private long date;
  private String information;

}
