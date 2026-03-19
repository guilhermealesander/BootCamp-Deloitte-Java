const state = {
    usuario: null,
    consultas: [],
    consultaEmEdicao: null,
    ultimoComprovante: null
};

const loginForm = document.getElementById("login-form");
const cadastroForm = document.getElementById("cadastro-form");
const editarUsuarioForm = document.getElementById("editar-usuario-form");
const consultaForm = document.getElementById("consulta-form");
const painelUsuario = document.getElementById("painel-usuario");
const consultasTabela = document.getElementById("consultas-tabela");
const comprovanteCard = document.getElementById("comprovante-card");
const comprovanteVazio = document.getElementById("comprovante-vazio");
const imprimirBtn = document.getElementById("imprimir-btn");
const toastContainer = document.getElementById("toast-container");
const consultaFormTitle = document.getElementById("consulta-form-title");
const consultaSubmitBtn = document.getElementById("consulta-submit-btn");
const cancelarRemarcacaoBtn = document.getElementById("cancelar-remarcacao-btn");

loginForm.addEventListener("submit", onLogin);
cadastroForm.addEventListener("submit", onCadastro);
editarUsuarioForm.addEventListener("submit", onAtualizarUsuario);
consultaForm.addEventListener("submit", onSalvarConsulta);
document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("excluir-usuario-btn").addEventListener("click", onExcluirUsuario);
document.getElementById("atualizar-consultas-btn").addEventListener("click", carregarConsultas);
cancelarRemarcacaoBtn.addEventListener("click", limparFormularioConsulta);
imprimirBtn.addEventListener("click", imprimirComprovante);

async function onLogin(event) {
    event.preventDefault();
    const payload = formToJson(loginForm);
    try {
        const usuario = await api("/api/usuarios/login", {
            method: "POST",
            body: JSON.stringify(payload)
        });
        ativarPainel(usuario);
        showToast("Login realizado com sucesso.", "success");
    } catch (error) {
        showToast(error.message, "danger");
    }
}

async function onCadastro(event) {
    event.preventDefault();
    const payload = formToJson(cadastroForm);
    try {
        const usuario = await api("/api/usuarios", {
            method: "POST",
            body: JSON.stringify(payload)
        });
        cadastroForm.reset();
        preencherLogin(usuario);
        showToast("Usuario cadastrado. Agora faca o login com CPF e Gmail.", "success");
    } catch (error) {
        showToast(error.message, "danger");
    }
}

async function onAtualizarUsuario(event) {
    event.preventDefault();
    if (!state.usuario) {
        return;
    }

    const payload = formToJson(editarUsuarioForm);
    try {
        const usuario = await api(`/api/usuarios/${state.usuario.id}`, {
            method: "PUT",
            body: JSON.stringify(payload)
        });
        state.usuario = usuario;
        renderUsuario();
        showToast("Cadastro alterado com sucesso.", "success");
    } catch (error) {
        showToast(error.message, "danger");
    }
}

async function onExcluirUsuario() {
    if (!state.usuario) {
        return;
    }

    const confirmado = window.confirm("Deseja realmente excluir este usuario? Esta açao tambem remove as consultas.");
    if (!confirmado) {
        return;
    }

    try {
        await api(`/api/usuarios/${state.usuario.id}`, {
            method: "DELETE"
        });
        logout();
        showToast("Usuario excluido com sucesso.", "warning");
    } catch (error) {
        showToast(error.message, "danger");
    }
}

async function onSalvarConsulta(event) {
    event.preventDefault();
    if (!state.usuario) {
        return;
    }

    const payload = formToJson(consultaForm);
    const emEdicao = Boolean(state.consultaEmEdicao);
    const url = emEdicao
        ? `/api/consultas/${state.consultaEmEdicao.id}/usuario/${state.usuario.id}`
        : `/api/consultas/usuario/${state.usuario.id}`;
    const method = emEdicao ? "PUT" : "POST";

    try {
        const consulta = await api(url, {
            method,
            body: JSON.stringify(payload)
        });
        renderComprovante(consulta, emEdicao ? "Consulta remarcada" : "Consulta agendada");
        limparFormularioConsulta();
        await carregarConsultas();
        showToast(emEdicao ? "Consulta remarcada com sucesso." : "Consulta marcada com sucesso.", "success");
    } catch (error) {
        showToast(error.message, "danger");
    }
}

async function carregarConsultas() {
    if (!state.usuario) {
        return;
    }

    try {
        state.consultas = await api(`/api/consultas/usuario/${state.usuario.id}`);
        renderConsultas();
    } catch (error) {
        showToast(error.message, "danger");
    }
}

function ativarPainel(usuario) {
    state.usuario = usuario;
    state.consultas = [];
    state.consultaEmEdicao = null;
    painelUsuario.classList.remove("d-none");
    renderUsuario();
    limparFormularioConsulta();
    carregarConsultas();
}

function renderUsuario() {
    if (!state.usuario) {
        return;
    }

    document.getElementById("usuario-nome").textContent = state.usuario.nome;
    document.getElementById("usuario-email").textContent = state.usuario.email;
    document.getElementById("usuario-detalhes").innerHTML = `
        <dt class="col-4">CPF</dt><dd class="col-8">${formatCpf(state.usuario.cpf)}</dd>
        <dt class="col-4">Telefone</dt><dd class="col-8">${formatPhone(state.usuario.telefone)}</dd>
        <dt class="col-4">Idade</dt><dd class="col-8">${state.usuario.idade} anos e ${state.usuario.meses} meses</dd>
        <dt class="col-4">Endereco</dt><dd class="col-8">${state.usuario.endereco}</dd>
    `;

    fillForm(editarUsuarioForm, state.usuario);
}

function renderConsultas() {
    if (!state.consultas.length) {
        consultasTabela.innerHTML = `
            <tr>
                <td colspan="6" class="text-center text-muted py-4">Nenhuma consulta agendada.</td>
            </tr>
        `;
        return;
    }

    consultasTabela.innerHTML = state.consultas.map((consulta) => `
        <tr>
            <td>${consulta.id}</td>
            <td>${formatDate(consulta.data)}</td>
            <td>${consulta.hora}</td>
            <td>${consulta.medico}</td>
            <td>${consulta.especialidade}</td>
            <td class="text-end">
                <div class="d-flex justify-content-end gap-2 flex-wrap">
                    <button type="button" class="btn btn-sm btn-outline-primary" data-action="editar" data-id="${consulta.id}">Remarcar</button>
                    <button type="button" class="btn btn-sm btn-outline-danger" data-action="excluir" data-id="${consulta.id}">Desmarcar</button>
                </div>
            </td>
        </tr>
    `).join("");

    consultasTabela.querySelectorAll("button").forEach((button) => {
        button.addEventListener("click", () => {
            const consultaId = Number(button.dataset.id);
            if (button.dataset.action === "editar") {
                prepararRemarcacao(consultaId);
                return;
            }
            desmarcarConsulta(consultaId);
        });
    });
}

function prepararRemarcacao(consultaId) {
    const consulta = state.consultas.find((item) => item.id === consultaId);
    if (!consulta) {
        return;
    }

    state.consultaEmEdicao = consulta;
    fillForm(consultaForm, consulta);
    consultaFormTitle.textContent = `Remarcar consulta #${consulta.id}`;
    consultaSubmitBtn.textContent = "Salvar remarcacao";
    cancelarRemarcacaoBtn.classList.remove("d-none");
    consultaForm.scrollIntoView({ behavior: "smooth", block: "start" });
}

async function desmarcarConsulta(consultaId) {
    if (!state.usuario) {
        return;
    }

    const confirmado = window.confirm("Deseja desmarcar esta consulta?");
    if (!confirmado) {
        return;
    }

    try {
        await api(`/api/consultas/${consultaId}/usuario/${state.usuario.id}`, {
            method: "DELETE"
        });
        if (state.consultaEmEdicao && state.consultaEmEdicao.id === consultaId) {
            limparFormularioConsulta();
        }
        await carregarConsultas();
        showToast("Consulta desmarcada com sucesso.", "warning");
    } catch (error) {
        showToast(error.message, "danger");
    }
}

function limparFormularioConsulta() {
    consultaForm.reset();
    state.consultaEmEdicao = null;
    consultaFormTitle.textContent = "Marcar consulta";
    consultaSubmitBtn.textContent = "Salvar consulta";
    cancelarRemarcacaoBtn.classList.add("d-none");
}

function renderComprovante(consulta, titulo) {
    state.ultimoComprovante = { consulta, titulo, usuario: state.usuario };
    comprovanteVazio.classList.add("d-none");
    comprovanteCard.classList.remove("d-none");
    imprimirBtn.classList.remove("d-none");
    comprovanteCard.innerHTML = `
        <div class="d-flex justify-content-between align-items-center flex-wrap gap-2 mb-4">
            <div>
                <span class="eyebrow">${titulo}</span>
                <h3 class="h4 mt-3 mb-1">Comprovante de agendamento</h3>
                <p class="text-muted mb-0">Apresente este comprovante ao comparecer na consulta.</p>
            </div>
            <div class="text-md-end">
                <div class="receipt-label">Protocolo</div>
                <div class="receipt-value">AG-${consulta.id}</div>
            </div>
        </div>
        <div class="receipt-grid">
            <div><span class="receipt-label">Paciente</span><span class="receipt-value">${state.usuario.nome}</span></div>
            <div><span class="receipt-label">CPF</span><span class="receipt-value">${formatCpf(state.usuario.cpf)}</span></div>
            <div><span class="receipt-label">Gmail</span><span class="receipt-value">${state.usuario.email}</span></div>
            <div><span class="receipt-label">Data</span><span class="receipt-value">${formatDate(consulta.data)}</span></div>
            <div><span class="receipt-label">Hora</span><span class="receipt-value">${consulta.hora}</span></div>
            <div><span class="receipt-label">Medico</span><span class="receipt-value">${consulta.medico}</span></div>
            <div><span class="receipt-label">Especialidade</span><span class="receipt-value">${consulta.especialidade}</span></div>
            <div><span class="receipt-label">Endereco</span><span class="receipt-value">${state.usuario.endereco}</span></div>
        </div>
    `;
}

function imprimirComprovante() {
    if (!state.ultimoComprovante) {
        return;
    }

    const { consulta, usuario, titulo } = state.ultimoComprovante;
    const printWindow = window.open("", "_blank", "width=900,height=700");
    if (!printWindow) {
        showToast("Nao foi possivel abrir a janela de impressao.", "danger");
        return;
    }

    printWindow.document.write(`
        <html lang="pt-BR">
        <head>
            <title>Comprovante de agendamento</title>
            <style>
                body { font-family: Arial, sans-serif; padding: 32px; color: #1f2937; }
                .card { border: 2px solid #0d6efd; border-radius: 18px; padding: 28px; }
                .badge { display: inline-block; background: #dbeafe; color: #1d4ed8; padding: 8px 14px; border-radius: 999px; font-size: 12px; font-weight: bold; text-transform: uppercase; }
                .grid { display: grid; grid-template-columns: repeat(2, minmax(0, 1fr)); gap: 16px; margin-top: 24px; }
                .label { display: block; font-size: 12px; text-transform: uppercase; color: #6b7280; margin-bottom: 4px; }
                .value { font-size: 16px; font-weight: 700; }
                h1 { margin: 16px 0 6px; }
                p { color: #4b5563; }
            </style>
        </head>
        <body>
            <div class="card">
                <span class="badge">${titulo}</span>
                <h1>Comprovante de agendamento</h1>
                <p>Protocolo AG-${consulta.id}</p>
                <div class="grid">
                    <div><span class="label">Paciente</span><span class="value">${usuario.nome}</span></div>
                    <div><span class="label">CPF</span><span class="value">${formatCpf(usuario.cpf)}</span></div>
                    <div><span class="label">Gmail</span><span class="value">${usuario.email}</span></div>
                    <div><span class="label">Data</span><span class="value">${formatDate(consulta.data)}</span></div>
                    <div><span class="label">Hora</span><span class="value">${consulta.hora}</span></div>
                    <div><span class="label">Medico</span><span class="value">${consulta.medico}</span></div>
                    <div><span class="label">Especialidade</span><span class="value">${consulta.especialidade}</span></div>
                    <div><span class="label">Endereco</span><span class="value">${usuario.endereco}</span></div>
                </div>
            </div>
        </body>
        </html>
    `);
    printWindow.document.close();
    printWindow.focus();
    printWindow.print();
}

function logout() {
    state.usuario = null;
    state.consultas = [];
    state.consultaEmEdicao = null;
    state.ultimoComprovante = null;
    painelUsuario.classList.add("d-none");
    consultasTabela.innerHTML = "";
    limparFormularioConsulta();
    comprovanteCard.classList.add("d-none");
    comprovanteVazio.classList.remove("d-none");
    imprimirBtn.classList.add("d-none");
}

async function api(url, options = {}) {
    const response = await fetch(url, {
        headers: {
            "Content-Type": "application/json",
            ...(options.headers || {})
        },
        ...options
    });

    if (response.status === 204) {
        return null;
    }

    const data = await response.json().catch(() => ({}));
    if (!response.ok) {
        throw new Error(readErrorMessage(data));
    }

    return data;
}

function readErrorMessage(data) {
    if (data.erro) {
        return data.erro;
    }

    const firstMessage = Object.values(data)[0];
    return typeof firstMessage === "string" ? firstMessage : "Ocorreu um erro inesperado.";
}

function formToJson(form) {
    const json = Object.fromEntries(new FormData(form).entries());
    ["idade", "meses"].forEach((field) => {
        if (field in json) {
            json[field] = Number(json[field]);
        }
    });
    return json;
}

function fillForm(form, data) {
    Array.from(form.elements).forEach((field) => {
        if (!field.name || !(field.name in data)) {
            return;
        }
        field.value = data[field.name] ?? "";
    });
}

function preencherLogin(usuario) {
    document.getElementById("login-cpf").value = formatCpf(usuario.cpf);
    document.getElementById("login-email").value = usuario.email;
}

function formatCpf(cpf) {
    const digits = String(cpf || "").replace(/\D/g, "");
    return digits.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
}

function formatPhone(phone) {
    const digits = String(phone || "").replace(/\D/g, "");
    if (digits.length === 11) {
        return digits.replace(/(\d{2})(\d{5})(\d{4})/, "($1) $2-$3");
    }
    return digits.replace(/(\d{2})(\d{4})(\d{4})/, "($1) $2-$3");
}

function formatDate(date) {
    return new Date(`${date}T00:00:00`).toLocaleDateString("pt-BR");
}

function showToast(message, type) {
    const wrapper = document.createElement("div");
    wrapper.className = "toast align-items-center border-0 text-bg-" + type;
    wrapper.role = "alert";
    wrapper.ariaLive = "assertive";
    wrapper.ariaAtomic = "true";
    wrapper.innerHTML = `
        <div class="d-flex">
            <div class="toast-body">${message}</div>
            <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    `;
    toastContainer.appendChild(wrapper);
    const toast = new bootstrap.Toast(wrapper, { delay: 3500 });
    toast.show();
    wrapper.addEventListener("hidden.bs.toast", () => wrapper.remove());
}
