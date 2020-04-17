package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.model.ContactModel;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.PersonRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private Gson gson = new Gson();

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ContactRepository contactRepository;

    @Test
    @WithMockUser
    public void shouldCreateContact() throws Exception {
        Person person = new Person();
        person.setFirstName("Dawid");
        person.setLastName("Witasezk");
        person = personRepository.save(person);
        ContactModel contactModel = new ContactModel();
        contactModel.setPhoneNumber("792343278");
        contactModel.setPersonId(person.getId());
        MockHttpServletRequestBuilder builder = post("/contact")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(contactModel));

          mockMvc.perform(builder)
                    .andDo(print())
                    .andExpect(status().isCreated())
                     .andReturn()
                     .getRequest()
                     .getContentAsString();
    }
}
