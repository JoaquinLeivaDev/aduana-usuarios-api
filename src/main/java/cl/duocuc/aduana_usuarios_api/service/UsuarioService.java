package cl.duocuc.aduana_usuarios_api.service;

import cl.duocuc.aduana_usuarios_api.client.AduanaClient;
import cl.duocuc.aduana_usuarios_api.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final AduanaClient aduanaClient;

    public ApiResponse<List<UsuarioResponseDTO>> obtenerTodos() {
        return aduanaClient.obtenerTodos();
    }

    public ApiResponse<UsuarioResponseDTO> buscarUsuarioPorId(Long id) {
        return aduanaClient.obtenerUsuarioPorId(id);
    }

    public ApiResponse<UsuarioResponseDTO> crearUsuario(UsuarioRequestDTO dto) {
        return aduanaClient.crearUsuario(dto);
    }

    public ApiResponse<UsuarioResponseDTO> actualizarUsuario(Long id, UsuarioRequestDTO dto) {
        return aduanaClient.actualizarUsuario(id, dto);
    }

    public ApiResponse<Void> eliminarUsuario(Long id) {
        return aduanaClient.eliminarUsuario(id);
    }

    public ApiResponse<List<ReporteResponseDTO>> obtenerReportesPorUsuario(Long id) {
        return aduanaClient.obtenerReportesPorUsuario(id);
    }
}