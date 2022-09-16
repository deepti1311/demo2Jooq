package io.tntra.demo2jooq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.tntra.demo2Jooq.tables.pojos.Student;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

public class Client {


    public static void main(String[] args) throws IOException, InterruptedException {

        var objectMapper =new ObjectMapper();
        objectMapper.findAndRegisterModules();

        var client = HttpClient.newHttpClient();
        HttpResponse<String> response= client.send(
                HttpRequest.newBuilder().GET()
                        .uri(URI.create("http://localhost:8080/listStudent")).build(),
                BodyHandlers.ofString());

        List<Student> student=objectMapper.readValue(response.body(),
                new TypeReference<List<Student>>() {
                });

        System.out.println(student);

        Student newStudent = new Student();
        newStudent.getFirstName("deepti");
        newStudent.getLastName("jakhotra");
        newStudent.getAge("22");
        newStudent.getEmail("deepti@gmail.com");
        newStudent.getCollageName("wsdgcyw aweygdy jsdfgc");

        String json= objectMapper.writeValueAsString(newStudent);
        response = client.send(
                HttpRequest.newBuilder().POST(BodyPublishers.ofString(json))
                        .header("Content-Type","application/json")
                        .uri(URI.create("http://localhost:8080/newStudent")).build(),
                BodyHandlers.ofString()
        );

        Student insertedStudent = objectMapper.readValue(response.body(),Student.class);
        System.out.println(insertedStudent);







    }
}
