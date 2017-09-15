package net.scriptgate.android.opengles.matrix;

import static android.opengl.GLES20.glUniformMatrix4fv;

public class ModelViewProjectionMatrix {

    /**
     * Allocate storage for the final combined matrix. This will be passed into the shader program.
     */
    private float[] mvpMatrix = new float[16];
    private float[] matrix;

    public void multiply(ModelMatrix modelMatrix, ViewMatrix viewMatrix, ProjectionMatrix projectionMatrix) {
        // This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        modelMatrix.multiplyWithMatrixAndStore(viewMatrix, mvpMatrix);
        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        projectionMatrix.multiplyWithMatrixAndStore(mvpMatrix);
    }

    public void passTo(int matrixHandle) {
        glUniformMatrix4fv(matrixHandle, 1, false, mvpMatrix, 0);
    }

    public void multiply(ModelMatrix modelMatrix, ViewMatrix viewMatrix) {
        // This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        modelMatrix.multiplyWithMatrixAndStore(viewMatrix, mvpMatrix);
    }

    public void multiply(ProjectionMatrix projectionMatrix) {
        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        projectionMatrix.multiplyWithMatrixAndStore(mvpMatrix);
    }

    public void multiply(ProjectionMatrix projectionMatrix, float[] temporaryMatrix) {
        projectionMatrix.multiplyWithMatrixAndStore(mvpMatrix, temporaryMatrix);
        System.arraycopy(temporaryMatrix, 0, mvpMatrix, 0, 16);
    }

    @Deprecated
    public float[] getMatrix() {
        return mvpMatrix;
    }
}
