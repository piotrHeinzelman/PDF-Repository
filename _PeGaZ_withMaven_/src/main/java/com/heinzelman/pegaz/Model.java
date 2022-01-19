package com.heinzelman.pegaz;

import com.heinzelman.pegaz.frontEnds.Window;
import lombok.ToString;

import java.util.Map;

@ToString
public class Model {

    private String activeProj;
    private Map<Long, String> mapOfProj;
    private Long projId;

    public String getActiveProj() { return activeProj; }
    public void setActiveProj(String activeProj) { this.activeProj = activeProj; }
    public Map<Long, String> getMapOfProj() { return mapOfProj; }
    public void setMapOfProj(Map<Long, String> mapOfProj) { this.mapOfProj = mapOfProj; }

    public void setProjId(Long projId) { this.projId = projId; }
    public Long getProjId() { return projId; }

}
