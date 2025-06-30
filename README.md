# 📱 Medication Reminder App 

## 📝 Descrição
Aplicativo de Lembrete de Medicamentos desenvolvido em Java para Android que ajuda os usuários a gerenciar sua medicação com lembretes automáticos.

## ✨ Funcionalidades Principais
- ✅ Cadastro de medicamentos com nome, dosagem e horário
- 🔔 Notificações automáticas no horário programado
- ✏️ Edição de medicamentos existentes
- 🗑️ Exclusão de medicamentos (nova funcionalidade)
- 📅 Listagem ordenada por horário de tomada
- 💾 Armazenamento local com Room Database

## 🛠️ Tecnologias Utilizadas
- **Linguagem**: Java
- **IDE**: Android Studio
- **Banco de Dados**: Room (SQLite)
- **Notificações**: AlarmManager
- **Layout**: XML (ConstraintLayout e LinearLayout)
- **Arquitetura**: LiveData + Room + RecyclerView

## 🗃️ Modelo do Banco de Dados
| Campo   | Tipo    | Descrição                     |
|---------|---------|-------------------------------|
| id      | int     | Chave primária (auto)         |
| name    | String  | Nome do medicamento          |
| dosage  | String  | Dosagem                       |
| hour    | int     | Hora do lembrete              |
| minute  | int     | Minuto do lembrete            |

## 📱 Telas do Aplicativo
1. **Tela Principal (MainActivity)**: Lista todos os medicamentos cadastrados
2. **Tela de Cadastro/Edição (AddEditMedicationActivity)**: Permite inserir ou editar informações
3. **Notificação**: Dispara no horário configurado com o nome do medicamento

## ⚙️ Regras de Negócio
1. Cadastro de medicamentos com nome, dosagem e horário
2. Armazenamento local usando Room Database
3. Agendamento automático de alarmes com AlarmManager
4. CRUD completo (Create, Read, Update, Delete)
5. Listagem ordenada cronologicamente
6. Notificações push no horário programado
7. Identificação única por ID automático

## 👨‍💻 Desenvolvedores
- Daniel Victor
- Izabela Gomes

## 📌 Considerações Finais
Aplicativo desenvolvido com foco em simplicidade e usabilidade para ajudar usuários a manterem sua medicação em dia. As notificações automáticas reforçam o compromisso com a saúde do usuário.

## 🚀 Como Executar
1. Clone o repositório
2. Abra o projeto no Android Studio
3. Execute em um emulador ou dispositivo Android

---

*Documentação atualizada em 01/07/2025*
