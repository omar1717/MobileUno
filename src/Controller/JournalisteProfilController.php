<?php

namespace App\Controller;

use Symfony\Component\HttpFoundation\Request;
use App\Entity\Journaliste;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Persistence\ManagerRegistry;
use Doctrine\ORM\EntityManagerInterface;


class JournalisteProfilController extends AbstractController
{
    public function __construct(private ManagerRegistry $registry) {}

    #[Route('/journaliste/profil', name: 'app_journaliste_profil')]
    public function index(): Response
    {
        $journaliste = $this->getUser();
        return $this->render('journaliste_profil/index.html.twig', [
            'controller_name' => 'JournalisteProfilController',
            'journaliste' => $journaliste,
        ]);
    }

    
    public function delete(Journaliste $journaliste, Request $request): Response
    {
        if ($this->isCsrfTokenValid('delete'.$journaliste->getId(), $request->request->get('_token'))) {
            $entityManager = $this->registry->getManager();
            $entityManager->remove($journaliste);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_home');
    }    

    /**
 * @Route("/journalistes/delete/{id}", name="delete_journaliste")
 */
public function delete_journaliste(Request $request, EntityManagerInterface $entityManager, $id)
{
    $journaliste = $entityManager->getRepository(Journaliste::class)->find($id);
    
    if (!$journaliste) {
        throw $this->createNotFoundException(
            'Aucun journaliste trouvé pour l\'identifiant '.$id
        );
    }
    
    $entityManager->remove($journaliste);
    $entityManager->flush();
    
    return $this->redirectToRoute('liste_journalistes');
}
}