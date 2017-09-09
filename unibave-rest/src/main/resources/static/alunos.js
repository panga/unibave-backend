var app = angular.module('alunos', ['ngResource']);

app.controller('AlunosController', function($scope, AlunosResource) {
    
    $scope.lista = function() {
        AlunosResource.query(function(data) {
            $scope.alunos = data;
        });
    };
    
    $scope.adicionar = function(aluno) {
        new AlunosResource(aluno).$save().then(function(data) {
            $scope.novoAluno = {};
            $scope.lista();
        });
    };
    
    $scope.atualizar = function(alunoResource) {
        alunoResource.$update().then(function(data) {
            $scope.lista();
        });
    };
    
    $scope.excluir = function(alunoResource) {
        alunoResource.$delete().then(function(data) {
            $scope.lista();
        });
    };

    $scope.lista();

});

app.factory("AlunosResource", function ($resource) {
    return $resource("/api/alunos/:codigo", {codigo: "@codigo"}, {
        'update': {method: 'PUT'},
        'query': {method: 'GET', isArray: true, transformResponse: function (response) {
            response = angular.fromJson(response);
            return response.content;
        }}
    });
});

app.factory('ErrorInterceptor', function ($q) {
    return {
        'responseError': function (response) {
            if (response.status == 401) {
                alert("Unauthorized");
            } else if (response.status == 403) {
                alert("Forbidden");
            } else if (response.status == 404) {
                alert("Not found");
            } else if (response.status) {
                if (response.data && response.data.message) {
                    alert(response.data.message);
                } else {
                    alert("An unexpected server error has occurred");
                }
            }
            return $q.reject(response);
        }
    };
});

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('ErrorInterceptor');
});