### Teste Técnico para Dev Java + Angular

#### Objetivo:
Avaliar a capacidade da(e/o) candidata(e/o) de lidar com bancos de dados, desenvolver um frontend adequado, seguir boas práticas de código, realizar testes unitários, escrever APIs REST e CRUDs competentes, trabalhar com código modular e manter código legado.

#### Cenário:
Você foi contratada(e/o) para desenvolver um sistema de gerenciamento de um mini mercado. Este sistema deve permitir que os administradores do mercado gerenciem produtos, categorias e estoques.

#### Estrutura do Teste:
O teste será dividido em três partes: Backend (Java), Frontend (Angular) e Integração. Cada parte terá tarefas específicas para avaliar diferentes competências.

---

### Parte 1: Backend (Java)

*Objetivo:* Avaliar a capacidade de desenvolver uma API RESTful com CRUDs, utilizando boas práticas de código, documentação e testes unitários.

*Tarefa:*
1. Crie uma API RESTful utilizando Spring Boot para gerenciar os seguintes recursos:
   - *Produto:* ID (gerado automaticamente), Nome, Descrição, Preço, Quantidade em estoque, Categoria (referência a uma Categoria).
   - *Categoria:* ID (gerado automaticamente), Nome, Descrição.

2. Implemente as operações CRUD (Create, Read, Update, Delete) para os recursos Produto e Categoria.

3. Configure o acesso ao banco de dados (H2, MySQL ou PostgreSQL) e crie as tabelas necessárias para armazenar os dados.

4. Documente a API utilizando Swagger.

5. Implemente testes unitários para os serviços e controladores da API utilizando JUnit e Mockito.

6. Escreva um README.md com instruções claras sobre como configurar e executar o projeto.

*Critérios de Avaliação:*
- Organização e clareza do código
- Boas práticas de desenvolvimento (SOLID, DRY, KISS)
- Documentação da API (Swagger)
- Qualidade dos testes unitários
- Configuração e instruções no README.md

---

### Parte 2: Frontend (Angular)

*Objetivo:* Avaliar a capacidade de desenvolver um frontend funcional e esteticamente agradável, integrando com a API RESTful desenvolvida na Parte 1.

*Tarefa:*
1. Crie uma aplicação Angular que consuma a API RESTful criada na Parte 1.

2. Implemente as seguintes funcionalidades no frontend:
   - Listar todos os produtos
   - Adicionar um novo produto
   - Atualizar um produto existente
   - Excluir um produto
   - Listar todas as categorias
   - Adicionar uma nova categoria
   - Atualizar uma categoria existente
   - Excluir uma categoria

3. Crie um layout responsivo e intuitivo utilizando Angular Material ou Bootstrap.

4. Implemente validações de formulário (ex: todos os campos são obrigatórios, o preço deve ser um número positivo, etc).

5. Implemente testes unitários para os componentes utilizando Jasmine e Karma.

6. Escreva um README.md com instruções claras sobre como configurar e executar o projeto.

*Critérios de Avaliação:*
- Organização e clareza do código
- Boas práticas de desenvolvimento Angular
- Qualidade e responsividade do layout
- Validações de formulário
- Qualidade dos testes unitários
- Configuração e instruções no README.md

---

### Parte 3: Integração e Manutenção de Código Legado

*Objetivo:* Avaliar a capacidade de integrar as partes desenvolvidas e manter um código legado.

*Tarefa:*
1. Integre o frontend e o backend desenvolvidos nas Partes 1 e 2.

2. Refatore o código legado fornecido, corrigindo bugs e melhorando a legibilidade, convertendo-o para o padrão MVP (Model-View-Presenter).

*Código Legado (Exemplo Simplificado):*
```java
import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateProductStock(Product product, int quantity) {
        if (product.getStock() - quantity < 0) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

public class ProductRepository {
    // Simulated database operations
    public void save(Product product) {
        // Save product to the database
    }

    public List<Product> findAll() {
        // Return all products from the database
        return List.of(); // Dummy return
    }
}

public class Product {
    private String name;
    private int stock;

    // getters and setters
}
```

*Tarefas de Refatoração:*
1. Separe a lógica de apresentação da lógica de negócios, convertendo o código para o padrão MVP.

2. Escreva testes unitários para o código legado refatorado.

3. Documente as mudanças feitas no código legado e as razões para cada alteração.

*Critérios de Avaliação:*
- Capacidade de integração entre frontend e backend
- Qualidade e clareza na refatoração do código legado
- Justificativas adequadas para as mudanças feitas
- Qualidade dos testes unitários do código legado
- Aderência ao padrão MVP

---

### Entrega
A(e/o) candidata(e/o) deverá entregar os seguintes itens:
- Código fonte do backend (Java) e frontend (Angular)
- Documentação (README.md) para ambos os projetos
- Testes unitários implementados
- Refatoração do código legado com justificativas documentadas

### Avaliação Final
Cada parte do teste será avaliada individualmente e, posteriormente, uma nota final será atribuída com base na soma dos pontos obtidos em cada critério de avaliação. A clareza, organização e a aderência às boas práticas serão fatores decisivos para a avaliação.

### Duração do Teste
Recomenda-se que a(e/o) candidata(e/o) tenha até 5 dias úteis para completar o teste técnico.

---

Boa sorte!