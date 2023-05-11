<?php

namespace App\Entity;

use App\Repository\JournalisteRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\PasswordAuthenticatedUserInterface;
use Symfony\Component\Security\Core\User\UserInterface;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;



#[ORM\Entity(repositoryClass: JournalisteRepository::class)]
#[UniqueEntity(fields: ['email'], message: 'There is already an account with this email')]
class Journaliste implements UserInterface, PasswordAuthenticatedUserInterface
{

    // ...

    /**
     * @ORM\OneToMany(targetEntity=Article::class, mappedBy="journaliste")
     */
    private $articles;

    public function __construct()
    {
        $this->articles = new ArrayCollection();
        $this->artcls = new ArrayCollection();
    }

    /**
     * @return Collection|Article[]
     */
    public function getArticles(): Collection
    {
        return $this->articles;
    }

    public function addArticle(Article $article): self
    {
        if (!$this->articles->contains($article)) {
            $this->articles[] = $article;
            $article->setJournaliste($this);
        }

        return $this;
    }

    public function removeArticle(Article $article): self
    {
        if ($this->articles->removeElement($article)) {
            // set the owning side to null (unless already changed)
            if ($article->getJournaliste() === $this) {
                $article->setJournaliste(null);
            }
        }

        return $this;
    }

    // ...
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 180, unique: true)]
    private ?string $email = null;

    #[ORM\Column]
    private array $roles = [];

    /**
     * @var string The hashed password
     */
    #[ORM\Column]
    private ?string $password = null;

    #[ORM\Column(length: 255)]
    private ?string $nom = null;

    #[ORM\Column(length: 255)]
    private ?string $prenom = null;

    #[ORM\Column(nullable: true)]
    private ?int $avertissement = null;

    #[ORM\Column(nullable: true)]
    private ?int $nbr_articles = null;

    #[ORM\OneToMany(mappedBy: 'IdJ', targetEntity: Article::class, orphanRemoval: true)]
    private Collection $artcls;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    /**
     * A visual identifier that represents this user.
     *
     * @see UserInterface
     */
    public function getUserIdentifier(): string
    {
        return (string) $this->email;
    }

    /**
     * @deprecated since Symfony 5.3, use getUserIdentifier instead
     */
    public function getUsername(): string
    {
        return (string) $this->email;
    }

    /**
     * @see UserInterface
     */
    public function getRoles(): array
    {
        $roles = $this->roles;
        // guarantee every user at least has ROLE_USER
        $roles[] = 'ROLE_USER';

        return array_unique($roles);
    }

    public function setRoles(array $roles): self
    {
        $this->roles = $roles;

        return $this;
    }

    /**
     * @see PasswordAuthenticatedUserInterface
     */
    public function getPassword(): string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    /**
     * Returning a salt is only needed, if you are not using a modern
     * hashing algorithm (e.g. bcrypt or sodium) in your security.yaml.
     *
     * @see UserInterface
     */
    public function getSalt(): ?string
    {
        return null;
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials()
    {
        // If you store any temporary, sensitive data on the user, clear it here
        // $this->plainPassword = null;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getAvertissement(): ?int
    {
        return $this->avertissement;
    }

    public function setAvertissement(?int $avertissement): self
    {
        $this->avertissement = $avertissement;

        return $this;
    }

    public function getNbrArticles(): ?int
    {
        return $this->nbr_articles;
    }

    public function setNbrArticles(?int $nbr_articles): self
    {
        $this->nbr_articles = $nbr_articles;

        return $this;
    }

    /**
     * @return Collection<int, Article>
     */
    public function getArtcls(): Collection
    {
        return $this->artcls;
    }

    public function addArtcl(Article $artcl): self
    {
        if (!$this->artcls->contains($artcl)) {
            $this->artcls->add($artcl);
            $artcl->setIdJ($this);
        }

        return $this;
    }

    public function removeArtcl(Article $artcl): self
    {
        if ($this->artcls->removeElement($artcl)) {
            // set the owning side to null (unless already changed)
            if ($artcl->getIdJ() === $this) {
                $artcl->setIdJ(null);
            }
        }

        return $this;
    }
}
