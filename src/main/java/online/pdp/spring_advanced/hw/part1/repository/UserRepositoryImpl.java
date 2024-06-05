package online.pdp.spring_advanced.hw.part1.repository;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import lombok.NonNull;
import online.pdp.spring_advanced.hw.part1.models.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository{

    CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
    CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

    MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(codecRegistry)
            .build();
    // "mongodb://localhost:27017/pdpjava"
    private final MongoClient CLIENT = MongoClients.create(settings);
    private final MongoDatabase DB = CLIENT.getDatabase("pdpjava");
    private final MongoCollection<Document> COLLECTION = DB.getCollection("users");

    @Override
    public User get(String id) {
        Bson filter = Filters.eq("_id", new ObjectId(id));
        Document document = COLLECTION.find(filter).first();
        if (document == null) {
            return null;
        }

        User user = new User(document);
        System.out.println(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        FindIterable<Document> documents = COLLECTION.find();

        for (Document document : documents) {
            users.add(new User(document));
        }

        users.forEach(n -> System.out.println(n));
        return users;
    }

    @Override
    public List<User> getAll(int page, int size) {
        List<User> users = new ArrayList<>();
        Bson sort = Sorts.ascending("id");


        Bson filter = Filters.or(Filters.regex("address.zipcode", ".*1$"),
                Filters.regex("address.zipcode", ".*4$"));

        FindIterable<Document> documents = COLLECTION.find(filter).sort(sort).skip(page * size).limit(size);

        for (Document document : documents) {
            users.add(new User(document));
        }

        users.forEach(n -> System.out.println(n));
        return users;
    }

    @Override
    public User save(@NonNull User user) {
        Map<String, Object> userToSave = Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "phone", user.getPhone(),
                "website", user.getWebsite(),
                "address", user.getAddress(),
                "company", user.getCompany()
        );

        Document document = new Document(userToSave);

        InsertOneResult insertOneResult = COLLECTION.insertOne(document);
        if (insertOneResult.wasAcknowledged()) {
            ObjectId objectId = insertOneResult.getInsertedId().asObjectId().getValue();
            user.setMongoId(objectId);
            return user;
        }

        return null;
    }

    @Override
    public List<User> saveAll(@NonNull List<User> users) {
        for (User user : users) {
            save(user);
        }
        return users;
    }

    @Override
    public boolean delete(String id) {
        Bson filter = Filters.eq("_id", new ObjectId(id));
        return COLLECTION.deleteOne(filter).wasAcknowledged();
    }

    @Override
    public boolean update(String userId, User user) {
        Bson filter = Filters.eq("_id", new ObjectId(userId));
        Document document = COLLECTION.find(filter).first();

        Bson updated = Updates.combine(
                Updates.set("id", user.getId()),
                Updates.set("name", user.getName()),
                Updates.set("username", user.getUsername()),
                Updates.set("email", user.getEmail()),
                Updates.set("phone", user.getPhone()),
                Updates.set("website", user.getWebsite()),
                Updates.set("address", user.getAddress()),
                Updates.set("company", user.getCompany())
        );

        return COLLECTION.updateOne(filter, updated).wasAcknowledged();
    }
}
