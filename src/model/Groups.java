package model;

public class Groups {

    private Integer groupId;

    private String descricao;

    public Groups() {
    }

    public Groups(String descricao) {
        this.descricao = descricao;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Groupo - Name: "  + descricao;
    }
}
