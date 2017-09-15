package net.scriptgate.android.opengles.program;

public enum UniformVariable {

    MV_MATRIX("u_MVMatrix"),
    MVP_MATRIX("u_MVPMatrix"),
    LIGHT_POSITION("u_LightPos"),
    TEXTURE("u_Texture");

    //TODO: ambiguous sucks
    //Font uniform variables
//    COLOR("u_Color");

    private final String name;

    UniformVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
