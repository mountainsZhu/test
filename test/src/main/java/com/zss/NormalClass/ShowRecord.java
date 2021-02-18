package com.zss.NormalClass;  //todo 包名首字母小写

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowRecord {
    private String name;
    private String dateStr;
    private String infomation;
}

