package is.hi.hbv501g.reithi.Persistence.Entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReviewSerializer extends JsonSerializer<Review> {
    private static final int MAX_OCCURRENCES = 2;
    private static int count;

    @Override
    public void serialize(Review review, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //Integer count = idCounts.get(review);
        jsonGenerator.writeStartObject();
        if (count != 0) {
            jsonGenerator.writeObjectField("user", review.getUser());
            jsonGenerator.writeObjectField("course", review.getCourse());
            System.out.println("BROBROBROBROBRO" + count);
            count = 0;

            //idCounts.put(review, 1);
        }
        else if (count <=1){
            count++;
            jsonGenerator.writeNumberField("user_id", review.getUser().getID());
            jsonGenerator.writeNumberField("course_id", review.getCourse().getID());
        }
        else{
            count++;
            jsonGenerator.writeNumberField("user_id", review.getUser().getID());
            jsonGenerator.writeNumberField("course_id", review.getCourse().getID());
        }
        /*else if (count < MAX_OCCURRENCES) {
            jsonGenerator.writeObjectField("user", review.getUser());
            jsonGenerator.writeObjectField("course", review.getCourse());
            idCounts.put(review, count + 1);
        } else {
            jsonGenerator.writeNumberField("user_id", review.getUser().getID());
            jsonGenerator.writeNumberField("course_id", review.getCourse().getID());
        }*/
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