package com.ircfront.start;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepository;
import org.gitective.core.CommitUtils;

import java.io.IOException;

public class GitTest {
  public static void main(String[] args) throws IOException {
    Repository repo2 = new FileRepository(".git");

    RevCommit latestCommit = CommitUtils.getHead(repo2);
    System.out.println("HEAD commit is " + latestCommit.name());
  }
}
