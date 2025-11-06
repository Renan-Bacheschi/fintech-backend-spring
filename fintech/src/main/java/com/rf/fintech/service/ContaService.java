package com.rf.fintech.service;

import com.rf.fintech.model.Conta;
import com.rf.fintech.model.Usuario;
import com.rf.fintech.repository.ContaRepository;
import com.rf.fintech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todas as contas
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    // Criar nova conta vinculada a um usuário
    public Conta criarConta(Long usuarioId, Conta conta) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        conta.setUsuario(usuario);
        return contaRepository.save(conta);
    }

    // Buscar conta por ID
    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    // Atr conta existente
    public Conta atualizarConta(Long id, Conta contaAtualizada) {
        Conta conta = buscarPorId(id);
        conta.setTipo(contaAtualizada.getTipo());
        conta.setSaldo(contaAtualizada.getSaldo());
        return contaRepository.save(conta);
    }

    // Deletar conta
    public void deletarConta(Long id) {
        Conta conta = buscarPorId(id);
        contaRepository.delete(conta);
    }
}
