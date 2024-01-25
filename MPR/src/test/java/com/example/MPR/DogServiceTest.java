package com.example.MPR;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DogServiceTest {
    @Mock
    private DogRepository repository;
    private AutoCloseable openMocks;
    private MyRestService myRestService;

    @BeforeEach
    public void init() {

        openMocks = MockitoAnnotations.openMocks(this);
        myRestService = new MyRestService(repository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    public void findFinds() {
        String name = "czarek";
        Dog dog = new Dog(9, name, "czarny");
        Mockito.when(repository.findByImie(name)).thenReturn(dog);

        Dog result = myRestService.getDogByName(name);
        assertEquals(dog, result);
    }

    @Test
    public void saveSaves() {
        String name = "czarek";
        int age = 5;
        Dog dog = new Dog(age, name, "brazowy");
        ArgumentCaptor<Dog> captor = ArgumentCaptor.forClass(Dog.class);
        Mockito.when(repository.save(captor.capture())).thenReturn(dog);

        myRestService.addDog(dog);
        Mockito.verify(repository, Mockito.times(1)).save(dog);
        Mockito.verify(repository, Mockito.times(1))
                .save(Mockito.any());
        Dog dogFromSaveCall = captor.getValue();
        assertEquals(dog, dogFromSaveCall);
    }
}
