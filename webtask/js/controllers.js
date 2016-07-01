var caminho = "http://localhost:8080";
var app = angular.module('app',['ngRoute']);
 
         app.config(['$routeProvider', function($routeProvider) {
            $routeProvider.            
            when('/', {
                templateUrl : 'views/home.html',
                controller     : 'HomeCtrl',
            }).            
            
            when('/cadastro', {
                templateUrl : 'views/cadastro.html',
                controller  : 'CadastroCtrl',
            }).
            
            when('/editar/:id?', { 
                templateUrl : 'views/cadastro.html',
                controller  : 'CadastroCtrl', 
            }).

            otherwise({
               redirectTo: '/'
            });
         }]);

app.controller('HomeCtrl', function($rootScope,$scope, $location,$http)
{
   $rootScope.activetab = $location.path();
	
    $scope.Listar = function(){
	
        $http.get(caminho + '/v1/tarefas').success(function(retorno){
                $scope.cadastros = retorno.tarefas;
        });
    }

    $scope.Excluir = function(item){
		var id = item.id;
        $http.delete(caminho + '/v1/tarefas/' + id).success(function(retorno){
            Materialize.toast('Tarefa excluida com sucesso!', 4000);
            $scope.Listar();
        });
    }

	
    $scope.Listar();
    
});

app.controller('CadastroCtrl', function($rootScope,$scope,$http, $location,$routeParams)
{
   $rootScope.activetab = $location.path();
   $scope.cadastro = {};
   $scope.cadastro.etiquetas = [];
   $scope.prioridades = ["Baixa","Media","Alta"]; 

    var contadorEtiqueta = 0;    
    
    $scope.AdicionarEtiqueta = function(){
        if(typeof($scope.novaEtiqueta) == 'undefined' || $scope.novaEtiqueta == ''){
            Materialize.toast('Informe um nome para a etiqueta!', 4000);
            return;
        }
        contadorEtiqueta ++;
        $scope.cadastro.etiquetas.push({id:contadorEtiqueta,descricao: $scope.novaEtiqueta})
        $scope.novaEtiqueta = '';
    }

    $scope.RemoveEtiqueta = function(){
        $scope.cadastro.etiquetas.splice(this,1);
    }

    /*Salva*/
    $scope.salvar = function(){
        console.log($scope.cadastro);
        $http.post(caminho + '/v1/tarefas',$scope.cadastro).success(function(retorno){
        Materialize.toast('Tarefa salva com sucesso!', 4000);            
    }).error(function(er){$('#msgerro').openModal();});
   }

   /*Editar Tarefa*/
   $scope.idTarefa =$routeParams.id;
   if($scope.idTarefa != undefined){       
       $http.get(caminho +'/v1/tarefas/' + $scope.idTarefa).success(function(retorno){
           $scope.cadastro = retorno;         
        }).error(function(er){$('#msgerro').openModal();});
    };

   $(document).ready(function(){
       $('select').material_select();
       $('.data').mask('00/00/0000');
   });
});


