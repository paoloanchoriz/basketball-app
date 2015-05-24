package com.panchoriz.myapp.repositories.documents;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QVenueDocument is a Querydsl query type for VenueDocument
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVenueDocument extends EntityPathBase<VenueDocument> {

    private static final long serialVersionUID = -1584129332L;

    public static final QVenueDocument venueDocument = new QVenueDocument("venueDocument");

    public final QAbstractDocument _super = new QAbstractDocument(this);

    public final StringPath city = createString("city");

    public final StringPath contact = createString("contact");

    public final NumberPath<Integer> courtType = createNumber("courtType", Integer.class);

    public final StringPath documentId = createString("documentId");

    public final NumberPath<Integer> flooringType = createNumber("flooringType", Integer.class);

    public final NumberPath<Long> latitude = createNumber("latitude", Long.class);

    public final NumberPath<Long> longitude = createNumber("longitude", Long.class);

    public final StringPath province = createString("province");

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final StringPath streetAddress = createString("streetAddress");

    public final StringPath venueName = createString("venueName");

    public QVenueDocument(String variable) {
        super(VenueDocument.class, forVariable(variable));
    }

    public QVenueDocument(Path<? extends VenueDocument> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVenueDocument(PathMetadata<?> metadata) {
        super(VenueDocument.class, metadata);
    }

}

