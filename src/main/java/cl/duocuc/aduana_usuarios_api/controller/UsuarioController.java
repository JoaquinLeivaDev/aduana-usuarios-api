package cl.duocuc.aduana_usuarios_api.controller;

import cl.duocuc.aduana_usuarios_api.dto.*;
import cl.duocuc.aduana_usuarios_api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> crear(
            @RequestBody @Valid UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.crearUsuario(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.eliminarUsuario(id));
    }

    @GetMapping("/{id}/reportes")
    public ResponseEntity<ApiResponse<List<ReporteResponseDTO>>> obtenerReportes(
            @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerReportesPorUsuario(id));
    }
}