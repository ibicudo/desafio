# desafio

README 
<strong>Arquivos json: </strong>
- O Arquivo users.json já possui 20 users criados , sendo que do 1 ao 10 são apenas users e do 11 ao 20 são sellers 

Arquivos (estão em resources): 
- post.json :  armazena os posts criados
- postPromo.json : armazena os posts promocionais criados
- sellers.json: armazena os vendedores
- users.json: armazena todos os usuários (incluindo os vendedores que estão em sellers, sendo que tem o mesmo id em ambos arquivos)	
- collection postman: desafioSpring

<strong>Para rodar o projeto, apenas start a aplicação e export a collection do postman que está disponível em um arquivo json no projeto na pasta resources.</strong> 
No Postman temos:

 <strong>US 0001-  followSeller(): </strong> 
- Para o User pode passar um ID de 1 a 20 (já cadastrados no arquivo)
- Para o Seller pode passar um ID de 11 a 20 (já cadastrados no arquivo)
</br></br>Casos de exception:
- User tentar seguir ele mesmo
- User tentar seguir um user que não é seller
</br></br>Como está a base():
- User 2 segue o 12 e o 20
- User 3 segue 15 e 11
- User 11 segue 20 
	

 <strong>US 0002 - getTotalFollowers(): </strong>
- Passar um ID de 11 a 20 (vendedores cadastrados)
- Vendedores que já tem seguidores : 11, 12, 15, 20


 <strong>US 0003 - getListFollowers (): </strong>
- Passar um ID de 11 a 20 
- Vendedores que já tem seguidores : 11, 12, 15, 20


 <strong>US 0004	- getFollowedList()  </strong>
- Passar um ID de 1 a 20
- Usuários ou vendedores que já estão seguindo algum vendedor da lista: 2 ,3, 11
- Os outros usuários não estão seguindo nenhum vendedor por enquanto, logo a lista virá vazia


 <strong>US 0005 - createPost():  </strong>
- userId tem que ser de 11 a 20 (sellers), caso não terá bad request
- O seller 11 já tem 3 posts criados no json e o 15 tem 1 post


 <strong>US 0006 - getPostsLastTwoWeeks(): </strong>
- Passar um ID de 1 a 20
- O user 3 já está seguindo vendedores que fizeram publicação de algum post


 <strong>US 0007 - unfollowSeller():  </strong>
- Passar ID de 1 a 20
- Users que já estão seguindo algum vendedor na base atual: 2, 3, 11
</br></br>Exceptions:
- Não é permitido ids iguais, não é permitido parar de seguir um vendedor que não existe  


 <strong>US 0008 - getFollowersASC (já está com parâmetro name_asc) </strong>
- Retorna lista de seguidores ordenado de forma crescente
- Passar ID de 11 a 20 


 <strong>US 0008 - getFollowersDESC  </strong>
- Retorna lista de seguidores ordenado de forma decrescente
- Passar ID de 11 a 20 


 <strong>US 0008 - getFollowedASC  </strong>
- Retorna lista de vendedores que um usuário segue ordenado de forma crescente
- Passar ID de 1 a 20 


 <strong>US 0008 - getFollowedDESC.  </strong>
- Retorna lista de vendedores que um usuário segue ordenado de forma decrescente
- Passar ID de 1 a 20 


 <strong>US 0009 - getPostsASC:   </strong>
- Passar a ID de 1 a 20 
- Na base , o id 3 já está seguindo vendedor que tem publicação de posts , os que não ao estiverem vai retornar vazio


 <strong>US 0009 - getPostsDESC  </strong>
- Passar a ID de 1 a 20 
- Na base , o id 3 já está seguindo vendedor que tem publicação de posts , os que não ao estiverem vai retornar vazio


 <strong>US 0010 - createPromoPost.  </strong>
- Passar payload conforme já está no postman
- Na base, já tem um post cadastrado para o userId 11 e dois para o userId 12


 <strong>US 0011 - countPromo.  </strong>
- Passar ID de 11 a 20 
- Ids 11 e 12 já têm um post promocional criado


 <strong>US 0012 - getListPromoPostBySeller  </strong>
- Passar ID de 11 a 20
- Somente o ID 11 e 12 tem posts promocionais


