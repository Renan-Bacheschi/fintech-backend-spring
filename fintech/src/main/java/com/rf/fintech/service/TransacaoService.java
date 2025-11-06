package com.rf.fintech.service;

import com.rf.fintech.model.Conta;
import com.rf.fintech.model.Transacao;
import com.rf.fintech.repository.ContaRepository;
import com.rf.fintech.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    // Listar todas as transações
    public List<Transacao> listarTransacoes() {
        return transacaoRepository.findAll();
    }

    // Buscar transação por ID
    public Transacao buscarPorId(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));
    }

    // Criar nova transação e atualizar saldo da conta
    public Transacao criarTransacao(Long contaId, Transacao transacao) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        // Regras do negócio
        if (transacao.getTipo().equalsIgnoreCase("DEPOSITO")) {
            conta.setSaldo(conta.getSaldo() + transacao.getValor());
        } else if (transacao.getTipo().equalsIgnoreCase("SAQUE")) {
            if (conta.getSaldo() < transacao.getValor()) {
                throw new RuntimeException("Saldo insuficiente");
            }
            conta.setSaldo(conta.getSaldo() - transacao.getValor());
        }

        contaRepository.save(conta);
        transacao.setConta(conta);
        return transacaoRepository.save(transacao);
    }

    // Att transação
    public Transacao atualizarTransacao(Long id, Transacao transacaoAtualizada) {
        Transacao transacao = buscarPorId(id);
        transacao.setTipo(transacaoAtualizada.getTipo());
        transacao.setValor(transacaoAtualizada.getValor());
        return transacaoRepository.save(transacao);
    }

    // Deletar transação
    public void deletarTransacao(Long id) {
        Transacao transacao = buscarPorId(id);
        transacaoRepository.delete(transacao);
    }
}
