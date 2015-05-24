package com.panchoriz.myapp.repositories.documents;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAbstractDocument is a Querydsl query type for AbstractDocument
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QAbstractDocument extends BeanPath<AbstractDocument<?>> {

    private static final long serialVersionUID = 706002427L;

    public static final QAbstractDocument abstractDocument = new QAbstractDocument("abstractDocument");

    public final SimplePath<Object> documentId = createSimple("documentId", Object.class);

    @SuppressWarnings("all")
    public QAbstractDocument(String variable) {
        super((Class)AbstractDocument.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QAbstractDocument(Path<? extends AbstractDocument> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    @SuppressWarnings("all")
    public QAbstractDocument(PathMetadata<?> metadata) {
        super((Class)AbstractDocument.class, metadata);
    }

}

