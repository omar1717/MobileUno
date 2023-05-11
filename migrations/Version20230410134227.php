<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230410134227 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE articles DROP FOREIGN KEY articles_ibfk_1');
        $this->addSql('DROP TABLE articles');
        $this->addSql('ALTER TABLE journaliste MODIFY id_journaliste INT NOT NULL');
        $this->addSql('DROP INDEX mail ON journaliste');
        $this->addSql('DROP INDEX mail_2 ON journaliste');
        $this->addSql('DROP INDEX `primary` ON journaliste');
        $this->addSql('ALTER TABLE journaliste ADD email VARCHAR(180) NOT NULL, ADD roles JSON NOT NULL, ADD password VARCHAR(255) NOT NULL, DROP mail, DROP motdepasse, DROP role, CHANGE id_journaliste id INT AUTO_INCREMENT NOT NULL, CHANGE nbr_article nbr_articles INT DEFAULT NULL');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_8FA00674E7927C74 ON journaliste (email)');
        $this->addSql('ALTER TABLE journaliste ADD PRIMARY KEY (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE articles (id INT AUTO_INCREMENT NOT NULL, id_journ INT NOT NULL, sujet VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, etat VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, INDEX id_journ (id_journ), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE articles ADD CONSTRAINT articles_ibfk_1 FOREIGN KEY (id_journ) REFERENCES journaliste (id_journaliste) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('DROP TABLE messenger_messages');
        $this->addSql('ALTER TABLE journaliste MODIFY id INT NOT NULL');
        $this->addSql('DROP INDEX UNIQ_8FA00674E7927C74 ON journaliste');
        $this->addSql('DROP INDEX `PRIMARY` ON journaliste');
        $this->addSql('ALTER TABLE journaliste ADD motdepasse VARCHAR(255) NOT NULL, ADD role VARCHAR(255) NOT NULL, DROP email, DROP roles, CHANGE id id_journaliste INT AUTO_INCREMENT NOT NULL, CHANGE password mail VARCHAR(255) NOT NULL, CHANGE nbr_articles nbr_article INT DEFAULT NULL');
        $this->addSql('CREATE UNIQUE INDEX mail ON journaliste (mail)');
        $this->addSql('CREATE INDEX mail_2 ON journaliste (mail)');
        $this->addSql('ALTER TABLE journaliste ADD PRIMARY KEY (id_journaliste)');
    }
}
