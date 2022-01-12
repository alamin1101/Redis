package com.geo.api.demo.masterdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RedisLookupData implements Serializable {


    private Long id;
    private String nameInBangla;
    private String nameInEnglish;
    private Boolean active;
    private short code;


}
