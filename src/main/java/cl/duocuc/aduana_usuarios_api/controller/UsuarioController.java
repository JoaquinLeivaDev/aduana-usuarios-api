package cl.duocuc.aduana_usuarios_api.controller;

import cl.duocuc.aduana_usuarios_api.dto.*;
import cl.duocuc.aduana_usuarios_api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Slf4j
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> listarTodos() {
        log.info("GET /api/v1/usuarios - Listando todos los usuarios");
        return ResponseEntity.ok(ApiResponse.ok(usuarioService.obtenerTodos(), "Usuarios obtenidos"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/v1/usuarios/{} - Recibiendo petición", id);
        return ResponseEntity.ok(ApiResponse.ok(usuarioService.buscarUsuarioPorId(id), "Usuario encontrado exitosamente"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> crear(
            @RequestBody @Valid UsuarioRequestDTO dto) {
        log.info("POST /api/v1/usuarios - Creando usuario: {}", dto.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok(usuarioService.crearUsuario(dto), "Usuario creado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> actualizar(
            @PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO dto) {
        log.info("PUT /api/v1/usuarios/{} - Actualizando usuario", id);
        return ResponseEntity.ok(ApiResponse.ok(usuarioService.actualizarUsuario(id, dto), "Usuario actualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/v1/usuarios/{} - Eliminando usuario", id);
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(ApiResponse.ok(null, "Usuario eliminado"));
    }

    @GetMapping("/{id}/reportes")
    public ResponseEntity<ApiResponse<List<ReporteResponseDTO>>> obtenerReportes(@PathVariable Long id) {
        log.info("GET /api/v1/usuarios/{}/reportes - Obteniendo reportes del usuario", id);
        return ResponseEntity.ok(ApiResponse.ok(usuarioService.obtenerReportesPorUsuario(id), "Reportes del usuario obtenidos"));
    }
}