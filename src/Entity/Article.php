<?php

namespace App\Entity;

use ApiPlatform\Metadata\ApiResource;
use App\Repository\ArticleRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;


#[ORM\Entity(repositoryClass: ArticleRepository::class)]
#[ApiResource]
class Article
{

    // ...

    /**
     * @ORM\ManyToOne(targetEntity=Journaliste::class)
     * @ORM\JoinColumn(nullable=false)
     */

     private $journaliste;

     // ...
 
     public function getJournaliste(): ?Journaliste
     {
         return $this->journaliste;
     }
 
     public function setJournaliste(?Journaliste $journaliste): self
     {
         $this->journaliste = $journaliste;
 
         return $this;
     }
 
     // ...
     
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    #[Groups("article")]

    private ?int $id = null;





    #[ORM\Column(length: 255)]
    #[Groups("article")]

    private ?string $etat = null;

    #[ORM\Column(length: 255)]
    #[Groups("article")]

    private ?string $sujet = null;

    #[ORM\ManyToOne(inversedBy: 'artcls')]
    #[ORM\JoinColumn(nullable: false)]
    #[Groups("article")]

    private ?Journaliste $IdJ = null;

    public function getId(): ?int
    {
        return $this->id;
    }



 

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

        return $this;
    }

    public function getSujet(): ?string
    {
        return $this->sujet;
    }

    public function setSujet(string $sujet): self
    {
        $this->sujet = $sujet;

        return $this;
    }

    public function getIdJ(): ?Journaliste
    {
        return $this->IdJ;
    }

    public function setIdJ(?Journaliste $IdJ): self
    {
        $this->IdJ = $IdJ;

        return $this;
    
    }

    
}
