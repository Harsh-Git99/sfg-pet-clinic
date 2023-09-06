package com.springframework.sfgpetclinic.controllers;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.net.ssl.SSLEngineResult;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> ownersSet;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ownersSet = new HashSet<>();
        ownersSet.add(Owner.builder().id(1L).build());
        ownersSet.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @Test
    void listOwner() throws Exception {
        when(ownerService.findAll()).thenReturn(ownersSet);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/index"))
                .andExpect(model().attribute("owners",hasSize(2)));
    }

    @Test
    void findOwner() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notImplemented"));
    }
}