<?php

namespace App\Controller;

use App\Entity\Article;
use App\Form\ArticleType;
use App\Repository\ArticleRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;

#[Route('/art')]
class ArtController extends AbstractController
{
    #[Route('/', name: 'app_art_index', methods: ['GET'])]
    public function index(ArticleRepository $articleRepository): Response
    {
        return $this->render('art/index.html.twig', [
            'articles' => $articleRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_art_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ArticleRepository $articleRepository): Response
    {

        $journaliste  = $this->getUser();
        $article = new Article();
        $article->setIdJ($journaliste);
        $form = $this->createForm(ArticleType::class, $article);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $articleRepository->save($article, true);

            return $this->redirectToRoute('app_art_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('art/new.html.twig', [
            'article' => $article,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_art_show', methods: ['GET'])]
    public function show(Article $article): Response
    {
        return $this->render('art/show.html.twig', [
            'article' => $article,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_art_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Article $article, ArticleRepository $articleRepository): Response
    {
        $form = $this->createForm(ArticleType::class, $article);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $articleRepository->save($article, true);

            return $this->redirectToRoute('app_art_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('art/edit.html.twig', [
            'article' => $article,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_art_delete', methods: ['POST'])]
    public function delete(Request $request, Article $article, ArticleRepository $articleRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$article->getId(), $request->request->get('_token'))) {
            $articleRepository->remove($article, true);
        }

        return $this->redirectToRoute('app_art_index', [], Response::HTTP_SEE_OTHER);
    }

    public function search(Request $request, ArticleRepository $userRepository)
    {
        $requestString = $request->get('q');
        $users = $userRepository->findUsersByString($requestString);
        if (!$users) {
            $result['article']['error'] = "User not found :(";
        } else {
            $result['article'] = $this->getRealEntities($users);
        }
        return new Response(json_encode($result));
    }

    public function getRealEntities($users)
    {
        foreach ($users as $user) {
            $realEntities[$user->getId()] = [
                $user->getId(),
                $user->getEtat(),
                $user->getSujet(),

                
            ];
        }
        return $realEntities;
    }
    public function sortByID(ArticleRepository $articleRepository): Response
    {
        $articles = $articleRepository->findBy([], ['id' => 'ASC']); // Tri des articles par ID (ascendant)
        return $this->render('article/index.html.twig', [
            'articles' => $articles,
        ]);
    }



 /**
 * @Route("/AllArticle", name="listt", methods={"GET"})
 */

 public function getArticle( ArticleRepository $articleRepository, SerializerInterface $normalizer){
    $article= $articleRepository->findAll();
    $ArticleNormalises = $normalizer->serialize($article, 'json', ['groups' => "article"]);
    $json = json_encode($ArticleNormalises);
    return new Response($json);

}



#[Route('addarticle/new', name: 'addJsonART', methods: ['GET', 'POST'])]
public function addarticle(Request $request, NormalizerInterface $normalizer){
    $em = $this->getDoctrine()->getManager();
    $Article = new Article();
    $Article -> setEtat($request->get('etat'));
    $Article -> setSujet($request->get('sujet'));
    $Article -> setIdJ($request->get('IdJ'));

    $em -> persist($Article);
    $em -> flush();

    $ArticleNormalises = $normalizer->normalize($Article, 'json', ['groups' => "article"]);
    return new Response(json_encode($ArticleNormalises));
}


#[Route('updatearticle/{"id"}', name: 'UpdateJsonART', methods: ['GET', 'POST'])]
public function updatearticle(Request $request, $id,  NormalizerInterface $normalizer){
    $em = $this->getDoctrine()->getManager();
    $Article = $em->getRepository(Article::class)->find($id);
    if ($Article === null) {
        return new Response("article not found", Response::HTTP_NOT_FOUND);
    }
    $Article -> setEtat($request->get('etat'));
    $Article -> setSujet($request->get('sujet'));
    $Article -> setIdJ($request->get('IdJ'));

    $em -> persist($Article);
    $em -> flush();

    $ArticleNormalises = $normalizer->normalize($Article, 'json', ['groups' => "Article"]);
    return new Response("Article updated successfully " . json_encode($ArticleNormalises));



}

#[Route('DelArticle/{id}', name: 'DELJsonART', methods: ['GET', 'POST'])]
public function DelArticle(Request $request, $id,  NormalizerInterface $normalizer){
    $em = $this->getDoctrine()->getManager();
    $article = $em->getRepository(Article::class)->find($id);
    $em -> remove($article);
    $em -> flush();

    $ArticleNormalises = $normalizer->normalize($article, 'json', ['groups' => "article"]);
    return new Response("article DELETED successfully " . json_encode($ArticleNormalises));
}





}
