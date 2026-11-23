package br.com.aula.biblioteca.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class LivroControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    @Test
    fun deveCadastrarLivroERetornar201() {
        mockMvc.perform(
            post("/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                      "titulo": "Java para Iniciantes",
                      "autor": "Maria Silva",
                      "ano": 2026
                    }
                    """.trimIndent()
                )
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.titulo").value("Java para Iniciantes"))
            .andExpect(jsonPath("$.autor").value("Maria Silva"))
            .andExpect(jsonPath("$.ano").value(2026))
    }
}
