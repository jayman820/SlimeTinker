package io.github.sefiraat.slimetinker.items.templates;

public class RodDefinition {
    private final String classType;
    private final String partType;
    private final String baseMaterial;
    private final String trimMaterial;
    private final String lineMaterial;

    public RodDefinition(String classType, String partType, String baseMaterial, String trimMaterial, String lineMaterial) {
        this.classType = classType;
        this.partType = partType;
        this.baseMaterial = baseMaterial;
        this.trimMaterial = trimMaterial;
        this.lineMaterial = lineMaterial;
    }

    public String getClassType() {
        return classType;
    }

    public String getPartType() {
        return partType;
    }

    public String getBaseMaterial() {
        return baseMaterial;
    }

    public String getTrimMaterial() {
        return trimMaterial;
    }

    public String getLineMaterial() {
        return lineMaterial;
    }
}
