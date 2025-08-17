using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public interface IPackageRepository
    {
      Task<IEnumerable<Package>> GetAllPackages();
        Task<Package?> GetPackageById(int id);
        Task AddPackage(Package package);
        Task UpdatePackage(Package package);
        Task DeletePackage(int id);
        Task<bool> PackageExists(int id);

        Task<IEnumerable<Package>> GetBySubcategoryIdAsync(int subcategoryId);
        Task<List<Package>> GetPackagesByCategoryAsync(int categoryId);

    }
}
