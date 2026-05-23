package cl.duocuc.aduana_usuarios_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
public class UsuarioRequestDTO {
    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotNull(message = "El ID del rol es obligatorio")
    private Long idRol;
}