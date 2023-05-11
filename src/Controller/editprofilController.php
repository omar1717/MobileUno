<?php
namespace App\Controller;


use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\JournalisteFormType;

class editprofilController extends AbstractController
{
    #[Route('/edit', name:'Update_journaliste',methods:["GET","POST"])]
   
    public function journalisteUpdate(Request $request): Response
    {
        $journaliste = $this->getUser();
    
        $form = $this->createForm(JournalisteFormType::class, $journaliste);
    
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
    
            return $this->redirectToRoute('app_journaliste_profil');
        }
    
        return $this->render('journaliste_profil/edit.html.twig', [
            'f' => $form->createView(),
        ]);
    }
}