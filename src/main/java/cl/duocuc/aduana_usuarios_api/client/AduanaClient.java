package cl.duocuc.aduana_usuarios_api.client;

import cl.duocuc.aduana_usuarios_api.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "aduana-api", url = "http://localhost:8080")
public interface AduanaClient {

    @GetMapping("/api/v1/usuarios")
    ApiResponse<List<UsuarioResponseDTO>> obtenerTodos();

    @GetMapping("/api/v1/usuarios/{id}")
    ApiResponse<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable Long id);

    @PostMapping("/api/v1/usuarios")
    ApiResponse<UsuarioResponseDTO> crearUsuario(@RequestBody UsuarioRequestDTO dto);

    @PutMapping("/api/v1/usuarios/{id}")
    ApiResponse<UsuarioResponseDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto);

    @DeleteMapping("/api/v1/usuarios/{id}")
    ApiResponse<Void> eliminarUsuario(@PathVariable Long id);

    @GetMapping("/api/v1/usuarios/{id}/reportes")
    ApiResponse<List<ReporteResponseDTO>> obtenerReportesPorUsuario(@PathVariable Long id);
}