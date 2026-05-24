package cl.duocuc.aduana_usuarios_api.controller;

import cl.duocuc.aduana_usuarios_api.dto.*;
import cl.duocuc.aduana_usuarios_api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ApiResponse<List<UsuarioResponseDTO>> listarTodos() { return usuarioService.obtenerTodos(); }

    @GetMapping("/{id}")
    public ApiResponse<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) { return usuarioService.buscarUsuarioPorId(id); }

    @PostMapping
    public ApiResponse<UsuarioResponseDTO> crear(@RequestBody @Valid UsuarioRequestDTO dto) { return usuarioService.crearUsuario(dto); }

    @PutMapping("/{id}")
    public ApiResponse<UsuarioResponseDTO> actualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO dto) { return usuarioService.actualizarUsuario(id, dto); }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Long id) { return usuarioService.eliminarUsuario(id); }

    @GetMapping("/{id}/reportes")
    public ApiResponse<List<ReporteResponseDTO>> obtenerReportes(@PathVariable Long id) { return usuarioService.obtenerReportesPorUsuario(id); }
}