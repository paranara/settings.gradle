package org.paranora.mapstruct.java.metadata.extractor;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class BaseElementClassMetaExtractor extends DefaultElementClassMetaExtractor {
    public BaseElementClassMetaExtractor(Types typesUtils, Elements elementUtils) {
        super(typesUtils, elementUtils);
    }

    @Override
    protected String readPackageName(TypeElement source) {
        Element e = this.typesUtils.asElement(source.asType());
        PackageElement pkg = this.elementUtils.getPackageOf(e);
        return pkg.getSimpleName().toString();
    }
}
