# Gerenciador de Tarefas – Documentação da API e Interface Web

Este repositório contém uma aplicação **Spring Boot** desenvolvida para gerenciar tarefas, permitindo criar, listar, atualizar e excluir registros. A aplicação conta com **interface Web (Thymeleaf)** e também **documentação automática via Swagger/OpenAPI**.

---

## 📌 **1. Como acessar a documentação da API (Swagger UI)**

Após rodar o projeto, acesse no navegador:

```
http://localhost:8080/swagger-ui/index.html
```

Caso o Swagger esteja configurado em outra rota, também tente:

```
http://localhost:8080/swagger-ui/
http://localhost:8080/swagger.html
```

---

## 🚀 **2. Como rodar o projeto**

### **Requisitos:**

* Java 17+
* Maven 3+
* MySQL ou outro banco configurado no `application.properties`

### **Rodando a aplicação:**

```
mvn spring-boot:run
```

OU

```
java -jar target/Gerenciador_de_Tarefas.jar
```

---

## 📂 **3. Estrutura do projeto**

```
com.jm.Gerenciador_de_Tarefas
 ├── Controller
 │     └── TarefaControllerUI.java
 ├── DTO
 ├── Model
 ├── Service
 ├── Repository
 └── resources/templates (HTML – Thymeleaf)
```

O **TarefaControllerUI** controla toda a parte visual (camada Web via Thymeleaf).

---

## 🖥️ **4. Rotas da Interface Web (UI)**

Base URL da interface:

```
/tarefas/ui
```

### ✔ **Listar tarefas**

```
GET /tarefas/ui/listar
```

Retorna a página `index.html` com todas as tarefas cadastradas.

---

### ✔ **Criar nova tarefa**

**Formulário:**

```
GET /tarefas/ui/criar
```

**Salvar no banco:**

```
POST /tarefas/ui/salvar
```

Envia um `TarefasDTO` para o service.

---

### ✔ **Editar tarefa**

**Carregar dados da tarefa:**

```
GET /tarefas/ui/editar/{id}
```

**Salvar alteração:**

```
POST /tarefas/ui/atualizar/{id}
```

Retorna para `/listar` com mensagem de sucesso.

---

### ✔ **Excluir tarefa**

```
GET /tarefas/ui/deletar/{id}
```

Após excluir, redireciona para `/listar`.

---

## 🧱 **5. Fluxo resumido do Controller UI**

| Ação             | Rota              | Método | View     | Service chamado     |
| ---------------- | ----------------- | ------ | -------- | ------------------- |
| Listar tarefas   | `/listar`         | GET    | index    | listarTarefas()     |
| Criar tarefa     | `/criar`          | GET    | criar    | —                   |
| Salvar tarefa    | `/salvar`         | POST   | redirect | criarTarefa()       |
| Editar tarefa    | `/editar/{id}`    | GET    | editar   | listarTarefaPorId() |
| Atualizar tarefa | `/atualizar/{id}` | POST   | redirect | alteraTarefas()     |
| Deletar tarefa   | `/deletar/{id}`   | GET    | redirect | deletarTarefa()     |

---

## 🧪 **6. Exemplos de JSON para requisições via API**

> Apenas para referência (API REST, não a interface UI)

### Criar/Atualizar tarefa

```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar controllers e services",
  "data": "2025-01-01",
  "status": "PENDENTE"
}
```

---

## 🗃️ **7. Templates HTML usados**

A interface UI do projeto usa os seguintes arquivos dentro de `/resources/templates`:

* `index.html` → lista de tarefas
* `criar.html` → formulário de criação
* `editar.html` → formulário de edição

Certifique-se de que esses arquivos existem para evitar erros `template not found`.

---

## 📘 **8. Sobre o Service chamado pelo Controller UI**

O `TarefaService` administra toda a lógica de negócio:

* `listarTarefas()`
* `listarTarefaPorId(id)`
* `criarTarefa(dto)`
* `alteraTarefas(id, dto)`
* `deletarTarefa(id)`

A UI apenas repassa os dados.

---

Ideia https://roadmap.sh/projects/task-tracker


