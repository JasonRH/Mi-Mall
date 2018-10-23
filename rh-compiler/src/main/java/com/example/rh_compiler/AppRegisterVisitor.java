package com.example.rh_compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;

/**
 * @author RH
 * @date 2018/10/23
 */
public class AppRegisterVisitor extends SimpleAnnotationValueVisitor8<Void, Void> {
    private final Filer FILER;
    private String mPackageName = null;

    public AppRegisterVisitor(Filer filer) {
        FILER = filer;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        generateJavaCode(t);
        return p;
    }

    //生成类文件
    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("AppRegister")
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.FINAL)
                        .superclass(TypeName.get(typeMirror))
                        .build();

        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                //注释
                .addFileComment("微信广播接收器")
                .build();
        try {
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
