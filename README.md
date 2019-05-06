App For Test Sua Música

Projeto para teste de entrevista.

## App construido com Dagger2, Retrofit, RxJava/Kotlin, Gson e Arquitetura MVP.

O principal objetivo da aplicação listar os eventos da api SeatGeek.

## Como o projeto está estruturado:

- AppTest
   - network (Retrofit e RxJava/Kotlin) - Retrofit responsável por manipular api e fazer comunição da mesma e RxJava/Kotlin responsável por fazer o gerenciamento do returno da api
      - SeatGeekNetwork
      
   - data (Gson) - Classes da dados já inicializadas com Gson(Serialize), facilitando a manipulação do restful.
      - SeatGeekData
        - EventsData
        - StatsData
        - TaxonomiesData
        - VenueData
     
   - di (Dagger)
      - component
        - ApplicationComponent - reponsável de fornece as instâncias dos module para classes injetadas
        
      - module
        - NetworkModule - Criar uma instância static do Retrofit e RxJava
        - ApplicationModule - Criar instâncias da Application e Gson
        
   - network - package responsável pelas interface do retrofit
     - SeatGeekNetwork
     
   - utils
     - Converts - class responsável por converts os objetos para gravar ou ler no banco de dados
     - EndlessRecyclerViewScrollListener - class responsável por deixar o recyclerview com infinite scroll
     - Utils - ferramentas para ajudar na estruturação
 
   - mvp
      - model - package responsável por puxar as informações da api ou persistência
        - Model
        - ModelInterface
        
      - presenter - package responsável por fazer o two-way entre model e view
        - Presenter 
        - PresenterInterface
   
      - ui - package responsável de configurar os dados vindo do presenter para views
        - MainActivity
        - MainActivityInterface
        
     - adapter - package responsável por mostrar as lista de eventos e detalhá-lo
       - details
          - DetailsSeatGeek
       - viewHolder
         - ProgressBar
         - SeatGeek
       - SeatGeekAdapter
     
      - interface - package responsável por criar listener do favoritar
        - ItemFavorite
      
      - room - package responsável por gravar os eventos favoritos
        - ControlDatabaseDAO
        - FavoriteDatabase
      
   - MainApplication - Inicialização do Dagger  
