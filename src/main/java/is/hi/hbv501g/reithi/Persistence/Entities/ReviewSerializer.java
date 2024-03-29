package is.hi.hbv501g.reithi.Persistence.Entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReviewSerializer extends JsonSerializer<Review> {
    private static final int MAX_OCCURRENCES = 2;

    private static Map<Object, Integer> idCounts = new HashMap<>();

    @Override
    public void serialize(Review review, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //Integer count = idCounts.get(review);
        jsonGenerator.writeStartObject();
        /*
        System.out.print(idCounts.toString());
        Integer count = idCounts.get(review);
        if (count == null) {
            jsonGenerator.writeObjectField("user", review.getUser());
            jsonGenerator.writeObjectField("course", review.getCourse());
            idCounts.put(review, 1);
        } else {
            jsonGenerator.writeNumberField("user_id", review.getUser().getID());
            jsonGenerator.writeNumberField("course_id", review.getCourse().getID());
        }
       else if (count < MAX_OCCURRENCES) {
            jsonGenerator.writeObjectField("user", review.getUser());
            jsonGenerator.writeObjectField("course", review.getCourse());
            idCounts.put(review, count + 1);
        }
        jsonGenerator.writeNumberField("user_id", review.getUser().getID());
        jsonGenerator.writeNumberField("course_id", review.getCourse().getID());

        */

        /*else if (count < MAX_OCCURRENCES) {
            jsonGenerator.writeObjectField("user", review.getUser());
            jsonGenerator.writeObjectField("course", review.getCourse());
            idCounts.put(review, count + 1);
        } else {
            jsonGenerator.writeNumberField("user_id", review.getUser().getID());
            jsonGenerator.writeNumberField("course_id", review.getCourse().getID());
        }*/
        jsonGenerator.writeNumberField("user_id", review.getUser().getID());
        jsonGenerator.writeNumberField("upvotes", review.getUpvotes());
        jsonGenerator.writeObjectField("upvoter_ids", review.getUpvoterIDs());
        jsonGenerator.writeObjectField("downvoter_ids", review.getDownvoterIDs());
        jsonGenerator.writeStringField("user_token", review.getUser().getDeviceToken());
        jsonGenerator.writeNumberField("course_id", review.getCourse().getID());
        jsonGenerator.writeStringField("user_name", review.getUser().getUserName());
        jsonGenerator.writeStringField("course_name", review.getCourse().getName());
        jsonGenerator.writeNumberField("ID", review.getID());
        jsonGenerator.writeNumberField("overallScore", review.getOverallScore());
        jsonGenerator.writeNumberField("difficulty", review.getDifficulty());
        jsonGenerator.writeNumberField("workload", review.getWorkload());
        jsonGenerator.writeNumberField("teachingQuality", review.getTeachingQuality());
        jsonGenerator.writeNumberField("courseMaterial", review.getCourseMaterial());
        jsonGenerator.writeStringField("comment", review.getComment());
        jsonGenerator.writeEndObject();
    }
}