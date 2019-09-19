package com.y3wegy.base.web.bean.web.push;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerMsg {
    private String content;

    @Override
    public String toString() {
        return content;
    }
}
